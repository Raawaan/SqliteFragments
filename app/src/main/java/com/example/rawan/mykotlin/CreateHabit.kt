package com.example.rawan.mykotlin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.rawan.mykotlin.db.HabitDbTable
import kotlinx.android.synthetic.main.create_habit.*
import java.io.IOException
import android.content.ContentResolver
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recycler_view.*


/**
 * Created by rawan on 8/14/18.
 */
class CreateHabit :android.support.v4.app.Fragment(){
    companion object {
        fun newInstance():CreateHabit {
            return CreateHabit()
        }
    }
   private val TAG = CreateHabit::class.java.simpleName
    private val CHOOSE_IMAGE_REQUEST = 1
    private var imageBitmap: Bitmap?= null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.create_habit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_save.setOnClickListener{
            storeHabit(this)
        }
        choose_img.setOnClickListener{
            chooseImage(view)
        }

    }
fun chooseImage(v:View){
    val intent = Intent()
    intent.type ="image/*"
    intent.action= Intent.ACTION_GET_CONTENT
    val chooser = Intent.createChooser(intent,"Choose Image")
    startActivityForResult(chooser, CHOOSE_IMAGE_REQUEST)
    Log.d(TAG,"Intent to choose image was sent...")
}

    fun storeHabit(v: CreateHabit){
        if (et_title.isBlank()||et_descritipon.isBlank()){
            Log.d(TAG,"title or description is blank!")
            DisplayErrorMsg("Your habit needs title and description")
            return
        }else if(imageBitmap==null){
            Log.d(TAG,"Image is blank!")
            DisplayErrorMsg("Your habit needs image")
            return
        }
        tv_error.visibility=View.INVISIBLE
        val title =et_title.text.toString()
        val description =et_descritipon.text.toString()
        val habit =DataClass(title,description,imageBitmap!!)
        val id = HabitDbTable(this.context).store(habit)
        if (id == -1L){
            DisplayErrorMsg("Could not " +
                    "be stored")
        }
        else{
            Toast.makeText(activity, "Swipe kda b2a :D", Toast.LENGTH_SHORT).show()
        }
    }
    fun DisplayErrorMsg(msg:String){
        tv_error.text=msg
        tv_error.visibility=View.VISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==CHOOSE_IMAGE_REQUEST&&resultCode==Activity.RESULT_OK&&data!=null&&data.data!=null){
Log.d(TAG,"image was chosen")
val bitmap= tryReadBitmap(data.data)
            bitmap?.let{
                iv_preview.visibility=View.VISIBLE
                iv_preview.setImageBitmap(bitmap)
                this.imageBitmap= bitmap
                Log.d(TAG,"READ IMAGE")
            }
        }
    }
    private fun tryReadBitmap(data: Uri):Bitmap?{
        return try{
            val resolver = activity!!.contentResolver

            MediaStore.Images.Media.getBitmap(resolver,data)
        }
        catch (e:IOException){
            e.printStackTrace()
            null
        }
    }
    private fun EditText.isBlank()= this.text.toString().isBlank()
}
