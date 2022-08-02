package com.joyrun.logger

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.joyrun.logger.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val log: File = cacheDir
        val buffer_path = log.absolutePath + File.separator + ".logger"
        val time = SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(Date())
        val log_path = log.absolutePath + File.separator + time + ".txt"
        // Example of a call to a native method
        val logger = Logger()
        val ptr = logger.initNative(buffer_path, 1034 * 400, log_path, false)
        binding.sampleText.text = ptr.toString()
        

        binding.btn.setOnClickListener {
            val start = System.currentTimeMillis()
            for (i in 0..100000) {
                logger.write(
                    ptr, "进程启动，该进程是APP运行的主进程，初始化工作应该该这里执行*******$i\n"
                )
            }
            logger.flushAsync(ptr)
            Log.e("asd", "耗时：${System.currentTimeMillis() - start}")
        }

    }

}