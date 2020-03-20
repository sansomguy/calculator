package com.joshsansom.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/**
 * Calculator Fragment class
 */
class CalculatorFragment : Fragment() {

    /**
     * Views
     */
    private lateinit var resultText: TextView;
    private lateinit var operandOne: EditText;
    private lateinit var operandTwo: EditText;

    /**
     * Operations
     */
    private fun add(operands: Pair<Double, Double>): Double {
        return operands.first + operands.second;
    }

    private fun subtract(operands: Pair<Double, Double>): Double {
        return operands.first - operands.second;
    }

    private fun multiply(operands: Pair<Double, Double>): Double {
        return operands.first * operands.second;
    }

    private fun divide(operands: Pair<Double, Double>): Double {
        if(operands.second === 0.0) {
            throw IllegalArgumentException("Division by zero not allowed");
        }
        return operands.first / operands.second;
    }

    /**
     * View Operations
     */

    private fun getOperandValues(): Pair<Double, Double> {
        val valueOne = operandOne.text.toString();
        val valueTwo = operandTwo.text.toString();

        return Pair(valueOne.toDouble(), valueTwo.toDouble());
    }

    private fun onClick(mathOperation: (operands: Pair<Double, Double>) -> Double) {
        try {
            resultText.text = mathOperation(getOperandValues()).toString();
        } catch (e: IllegalArgumentException) {
            // if we attempt to perform illegal operation, show appropriate message
            resultText.text = e.message;
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultText = view.findViewById(R.id.resultText);
        operandOne = view.findViewById(R.id.firstOperand);
        operandTwo = view.findViewById(R.id.firstOperand2);

        /**
         * Perform operations
         */
        view.findViewById<Button>(R.id.plusButton).setOnClickListener {
            onClick(::add);
        }

        view.findViewById<Button>(R.id.minusButton).setOnClickListener {
            onClick(::subtract);
        }

        view.findViewById<Button>(R.id.multiplyButton).setOnClickListener {
            onClick(::multiply)
        }

        view.findViewById<Button>(R.id.divideButton).setOnClickListener {
            onClick(::divide);
        }
    }
}
