package com.project.homework4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.project.homework4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkState()
    }

    private fun checkState() {
        binding.switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            for (i in 0 until binding.llMaterialCheckBox.childCount) {
                val childView = binding.llMaterialCheckBox.getChildAt(i)
                childView.isEnabled = isChecked
            }
        }
    }


}