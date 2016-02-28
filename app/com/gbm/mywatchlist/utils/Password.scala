package com.gbm.mywatchlist.utils

import java.security.MessageDigest

/**
  * Created by gbmetzner on 11/5/15.
  */
object Password {

  implicit class EncryptPassword(password: String) {
    def encryptPassword: String = {
      val algorithm: MessageDigest = MessageDigest.getInstance("SHA-256")
      algorithm.reset()
      algorithm.update(password.getBytes)
      getHexString(algorithm.digest)
    }
  }

  private def getHexString(messageDigest: Array[Byte]): String = {
    val hexString: StringBuffer = new StringBuffer
    messageDigest foreach {
      digest =>
        val hex = Integer.toHexString(0xFF & digest)
        if (hex.length == 1) hexString.append('0') else hexString.append(hex)
    }
    hexString.toString
  }
}
