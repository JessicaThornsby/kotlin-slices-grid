package com.jessicathornsby.kotlinslices

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
class MySliceProvider : SliceProvider() {

    override fun onCreateSliceProvider(): Boolean {
        return true
    }
    override fun onBindSlice(sliceUri: Uri): Slice? {

        val path = sliceUri.path
        when (path) {

            "/launchMainActivity" -> return createSliceWithGridRow(sliceUri)
        }
        return null
    }
    fun createSliceWithGridRow(sliceUri: Uri): Slice {
        return ListBuilder(context, sliceUri, ListBuilder.INFINITY)
                .setHeader {
                    it.apply {
                        setTitle("Want to start learning Kotlin for Android?")
                        setSubtitle("Check out these articles!")
                    }
                }

//Add a grid row to the list builder//

                .addGridRow {
                    it.apply {

                        //Add a cell to the row//

                        addCell {
                            it.apply {

                                //Add content to your cell//

                                addTitleText("Java vs. Kotlin")
                                addText("Part 1")
                                addImage(IconCompat.createWithResource(context, R.drawable.kotlin), ListBuilder.LARGE_IMAGE)

//Specify the intent that should trigger whenever the user interacts with this cell//

                                        .setContentIntent(
                                                loadArticlePendingIntent(
                                                        context,
                                                        "https://code.tutsplus.com/articles/java-vs-kotlin-should-you-be-using-kotlin-for-android-development--cms-27846?_ga=2.248953604.2056323917.1529001689-1662212337.1519829848"))
                            }
                        }
                        addCell {
                            it.apply {
                                addTitleText("Coding in Kotlin")
                                addText("Part 2")
                                addImage(IconCompat.createWithResource(context, R.drawable.kotlin), ListBuilder.LARGE_IMAGE)
                                        .setContentIntent(
                                                loadArticlePendingIntent(
                                                        context,
                                                        "https://code.tutsplus.com/tutorials/start-developing-android-apps-with-kotlin-part-1--cms-27827?_ga=2.245145222.2056323917.1529001689-1662212337.1519829848"))
                            }
                        }
                        addCell {
                            it.apply {
                                addTitleText("Lambdas & NPE")
                                addText("Part 3")
                                addImage(IconCompat.createWithResource(context, R.drawable.kotlin), ListBuilder.LARGE_IMAGE)
                                        .setContentIntent(
                                                loadArticlePendingIntent(
                                                        context,
                                                        "https://code.tutsplus.com/articles/coding-functional-android-apps-in-kotlin-lambdas-null-safety-more--cms-27964"))
                            }
                        }
                    }
                }
                .build()
    }
    private fun loadArticlePendingIntent(context: Context, url: String) =
            PendingIntent.getActivity(
                    context,
                    0,
                    Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) },
                    0
            )
}
