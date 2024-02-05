package org.example

import java.io.*
import java.net.Socket
import java.util.Scanner

fun main() {
    try {
        val socket = Socket("127.0.0.1",9999)
        val read = Scanner(System.`in`)
        println("Enter file path")
        val path = read.next()
        sendFile(path,socket)
    } catch (e: Exception){
        e.printStackTrace()
    }
}

fun sendFile(path: String,client: Socket) {
    try {
        val outputStream = DataOutputStream(client.getOutputStream())
        val byteContent = File(path).readBytes()

        outputStream.writeUTF(File(path).name)
//        outputStream.writeInt(byteContent.size)

        outputStream.write(byteContent,0, byteContent.size)

        outputStream.close()
        client.close()
        println("File sent!")
    } catch (e: Exception){
        e.printStackTrace()
    }
}