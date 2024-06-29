package com.example.adoptapuppy

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val breeds = arrayOf("Beagle", "German Shepherd", "Golden Retriever", "Rottweiler")
        val breedChipGroup: ChipGroup = findViewById(R.id.breed_chip_group)

        for (breed in breeds) {
            val chip = Chip(this)
            val drawable = ChipDrawable.createFromAttributes(this, null, 0, com.google.android.material.R.style.Widget_MaterialComponents_Chip_Filter)
            chip.setChipDrawable(drawable)
            chip.id = View.generateViewId()
            chip.text = breed
            breedChipGroup.addView(chip)
        }

        val adapter = QuickGroupRecyclerViewAdapter(applicationContext)
        adapter.items = arrayOf(
            QuickGroupItem("All", ResourcesCompat.getDrawable(resources, R.mipmap.puppy_all, null)),
            QuickGroupItem("Favorites", ResourcesCompat.getDrawable(resources, R.mipmap.puppy_favorites, null)),
            QuickGroupItem("Available", ResourcesCompat.getDrawable(resources, R.mipmap.puppy_available, null)),
            QuickGroupItem("Playful", ResourcesCompat.getDrawable(resources, R.mipmap.puppy_playful, null)),
            QuickGroupItem("Calm", ResourcesCompat.getDrawable(resources, R.mipmap.puppy_calm, null)),
            QuickGroupItem("Puppy", ResourcesCompat.getDrawable(resources, R.mipmap.puppy_puppy, null)),
            QuickGroupItem("Trained", ResourcesCompat.getDrawable(resources, R.mipmap.puppy_trained, null)),
        )

        val quickGroupItemsRecyclerView = findViewById<RecyclerView>(R.id.quick_group_all)
        quickGroupItemsRecyclerView.adapter = adapter
        quickGroupItemsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val postAdapter = PostRecyclerViewAdapter(applicationContext)

        postAdapter.items = arrayOf(
            Post("Fido", 3, "Beagle", true, true, "https://images.pexels.com/photos/1031466/pexels-photo-1031466.jpeg"),
            Post("Rex", 4, "Labrador", false, false, "https://images.pexels.com/photos/298062/pexels-photo-298062.jpeg"),
            Post("Buddy", 2, "Golden retriever", false, false, "https://images.pexels.com/photos/92380/pexels-photo-92380.jpeg"),
            Post("Max", 5, "German shepherd", true, true, "https://images.pexels.com/photos/2695827/pexels-photo-2695827.jpeg"),
            Post("Charlie", 3, "Poodle", true, true, "https://images.pexels.com/photos/373467/pexels-photo-373467.jpeg"),
            Post("Cooper", 1, "Dachshund", true, false, "https://images.pexels.com/photos/1139794/pexels-photo-1139794.jpeg"),
        )

        val postRecyclerView = findViewById<RecyclerView>(R.id.posts_container)
        postRecyclerView.adapter = postAdapter
        postRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}