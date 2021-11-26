package com.example.katya21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

data class Product(
        val ID: Int,
        val Title: String,
        val ProductTypeID: Int,
        val ArticleNumber: Int,
        val Description: String?,
        val Image: String,
        val ProductionPersonCount: Int,
        val ProductionWorkshopNumber: Int,
        val MinCostForAgent: Int
)