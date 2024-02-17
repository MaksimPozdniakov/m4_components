package com.project.homework4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.android.material.checkbox.MaterialCheckBox
import com.project.homework4.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editName.addTextChangedListener { conditionButtonSave() }
        binding.editPhone.addTextChangedListener { conditionButtonSave() }
        binding.radioButton1.setOnCheckedChangeListener { _, _ -> conditionButtonSave() }
        binding.radioButton2.setOnCheckedChangeListener { _, _ -> conditionButtonSave() }
        binding.switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            conditionButtonSave()
            for (i in 0 until binding.llMaterialCheckBox.childCount) {
                val childView = binding.llMaterialCheckBox.getChildAt(i)
                childView.isEnabled = isChecked
                if (childView is MaterialCheckBox) {
                    childView.setOnCheckedChangeListener { _, _ -> conditionButtonSave() }
                }
            }
        }

        progressBarMethod()
        conditionButtonSave()
        actionButtonSave()
    }

    private fun progressBarMethod() {
        val rnd = Random.nextInt(101)
        binding.progressBar.progress = rnd

        val progressText = getString(R.string.progress_format, rnd)
        binding.progressBarCounter.text = progressText
    }

    private fun conditionButtonSave() {
        val checkFieldName = binding.editName.text.toString().length < 40 &&
                binding.editName.text.toString().isNotEmpty()
        val checkFieldPhone = binding.editPhone.text.toString().isNotEmpty()
        val checkMaleButton = binding.radioButton1.isChecked
        val checkFemaleButton = binding.radioButton2.isChecked
        val checkedSwitch = binding.switchMaterial.isChecked

        val childInLinearLayout = binding.llMaterialCheckBox.childCount
        var checkedNotification = false

        for (i in 0 until childInLinearLayout) {
            val childView = binding.llMaterialCheckBox.getChildAt(i)
            if (childView is MaterialCheckBox && childView.isChecked) {
                checkedNotification = true
                break
            }
        }

        if (!checkedSwitch) {
            binding.btSave.isEnabled = false
            return
        }

        binding.btSave.isEnabled = (
            checkFieldName &&
            checkFieldPhone &&
            (checkMaleButton || checkFemaleButton) &&
                    checkedNotification)
    }
    private fun actionButtonSave() {
        binding.btSave.setOnClickListener {
            Toast.makeText(this, "Изменения сохранены!", Toast.LENGTH_SHORT).show()
        }
    }
}