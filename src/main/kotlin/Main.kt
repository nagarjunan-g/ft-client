package org.example

import java.io.*
import java.net.Socket
import java.util.Scanner
import java.util.UUID

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
        val saveFolder = "send"
        val filename = File(path).name

        val outputStream = DataOutputStream(client.getOutputStream())
        val byteContent = File(saveFolder,filename).readBytes()

        outputStream.writeUTF(renameFileWithUUID(filename))
        outputStream.write(byteContent,0, byteContent.size)

        outputStream.close()
        client.close()
        println("File sent!")
    } catch (e: Exception){
        e.printStackTrace()
    }
}

fun renameFileWithUUID(filename: String): String{
    val name = filename.split(".")
    val uuidFile = "${name[0]}-${UUID.randomUUID()}.${name[1]}"
    return uuidFile
}