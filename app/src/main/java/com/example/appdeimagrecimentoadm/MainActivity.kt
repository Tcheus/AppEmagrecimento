package com.example.appdeimagrecimentoadm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.set
import com.example.appdeimagrecimentoadm.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.PublicarRotina.setOnClickListener {

            val titulo = binding.editTituloRotina.text.toString()
            val noticia = binding.editrotina.text.toString()
            val data = binding.editDataRotina.text.toString()
            val autor = binding.editAutorRotina.text.toString()

            if (titulo.isEmpty()  || noticia.isEmpty()   || data.isEmpty()  || autor.isEmpty()){
                Toast.makeText(this,"Preencha todos os campos!",Toast.LENGTH_SHORT).show()
            }else{
                salvarNoticia(titulo, noticia, data, autor)
            }

        }
    }
    private fun salvarNoticia(titulo: String, noticia: String, data: String, autor: String){

        val mapNoticias = hashMapOf(
            "Titilo" to titulo,
            "Noticia" to noticia,
            "Data" to data,
            "Autor" to autor
        )

        db.collection("noticas").document("noticia")
            .set(mapNoticias).addOnCompleteListener {tarefa ->
                if (tarefa.isSuccessful){
                    Toast.makeText(this,"Rotina publicada com sucesso!",Toast.LENGTH_SHORT).show()
                    limparCampus()
                }
            }.addOnFailureListener{

            }
    }
    private fun limparCampus(){
        binding.editTituloRotina.setText("")
        binding.editDataRotina.setText("")
        binding.editrotina.setText("")
        binding.editAutorRotina.setText("")
    }
}