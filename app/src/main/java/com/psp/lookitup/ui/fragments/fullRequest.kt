package com.psp.lookitup.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.psp.lookitup.R


class fullRequest : Fragment() {

    val TAG = "Full Request Fragment"
    val dbref = Firebase.firestore
    val intent = Intent(Intent.ACTION_SEND)

}