package com.beleavemebe.solevarnya

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.core.os.postDelayed
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)

    private val height: Double?
        get() = try {
            binding.etHeight.text.toString().toDouble()
        } catch (e: Exception) {
            null
        }

    private val weight: Double?
        get() = try {
            binding.etWeight.text.toString().toDouble()
        } catch (e: Exception) {
            null
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCalculateButton()
        attachAdaptersToDropdownMenus()
    }

    private fun initCalculateButton() {
        binding.btnCalculate.setOnClickListener {
            val weightNotNull = weight?.toKilos()
                ?: return@setOnClickListener toast(R.string.weight_not_set)
            val heightNotNull = height?.toMeters()
                ?: return@setOnClickListener toast(R.string.height_not_set)

            Log.d("MainActivity", "$weightNotNull $heightNotNull")

            mockLoading()
            recalculateBMI(weightNotNull, heightNotNull)
            handler.postDelayed(750L) {
                endLoading()
            }
        }
    }

    private fun mockLoading() {
        binding.apply {
            progressIndicator.isIndeterminate = true
            progressIndicator.isVisible = true
            setBmiInfoVisible(false)
            btnCalculate.apply {
                isEnabled = false
                text = ""
            }
        }
    }

    private fun recalculateBMI(weight: Double, height: Double) {
        val bmi: Double =
            BmiCalculator.calculateBMI(weight, height)

        binding.tvBmiValue.text = "%.2f".format(bmi)
        binding.tvBmiDescription.text = BmiDescriptor.getBmiDescription(bmi)
    }

    private fun endLoading() {
        setBmiInfoVisible(true)
        binding.progressIndicator.isVisible = false
        binding.btnCalculate.apply {
            text = getString(R.string.calculate)
            isEnabled = true
        }
    }

    private fun attachAdaptersToDropdownMenus() {
        binding.apply {
            tiHeightUnit.attachAdapter(this@MainActivity, R.array.array_height_units)
            tiWeightUnit.attachAdapter(this@MainActivity, R.array.array_weight_units)
        }
    }

    private fun setBmiInfoVisible(flag: Boolean) {
        binding.apply {
            setOf(tvBmiDescription, tvBmiDescriptionTitle, tvBmiValue, tvBmiValueTitle)
                .forEach {
                    it.isVisible = flag
                }
        }
    }

    private val pickedHeightUnit: String
        get() = binding.tvHeightUnit.text.toString()

    private fun Double.toMeters(): Double {
        val measuringUnit: HeightUnit =
            when (pickedHeightUnit) {
                getString(R.string.ft) -> HeightUnit.Feet
                getString(R.string.centimeters) -> HeightUnit.Centimeter
                else -> throw IllegalStateException("Height unit string not registered in values/arrays.xml")
            }
        return measuringUnit.toMeters(this)
    }

    private val pickedWeightUnit: String
        get() = binding.tvWeightUnit.text.toString()

    private fun Double.toKilos(): Double {
        val measuringUnit: WeightUnit =
            when (pickedWeightUnit) {
                getString(R.string.lbs) -> WeightUnit.Pound
                getString(R.string.kg) -> WeightUnit.Kilogram
                else -> throw IllegalStateException("Weight unit string not registered in values/arrays.xml")
            }
        return measuringUnit.toKilos(this)
    }
}

private fun Context.toast(@StringRes stringResId: Int) {
    Toast.makeText(
        this,
        getString(stringResId),
        Toast.LENGTH_SHORT
    ).show()
}

private val handler
    get() = Handler(Looper.getMainLooper())

private fun TextInputLayout.attachAdapter(
    context: Context,
    @ArrayRes stringArrayResId: Int
) {
    val stringArray = context.resources.getStringArray(stringArrayResId)
    val adapter = ArrayAdapter(context, R.layout.menu_item, stringArray)
    (this.editText as AutoCompleteTextView).let {
        it.setText(stringArray[0])
        it.setAdapter(adapter)
    }
}