package com.rquimbiulco.pokedex.data.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import com.rquimbiulco.pokedex.domain.security.PasswordEncryptor
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.spec.MGF1ParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.OAEPParameterSpec
import javax.crypto.spec.PSource
import javax.inject.Inject

class PasswordEncryptorImpl @Inject constructor() : PasswordEncryptor {
    private val keyStore: KeyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply {
        load(null)
    }
    private val OAEP_SPEC = OAEPParameterSpec(
        MD_NAME,
        MGF,
        MGF1ParameterSpec.SHA1,
        PSource.PSpecified.DEFAULT
    )

    init {
        generateKeyPairIfNeeded()
    }

    override fun encrypt(password: String): String {
        val publicKey = getKeyPair().public

        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey, OAEP_SPEC)

        val encryptedBytes = cipher.doFinal(
            password.toByteArray(Charsets.UTF_8)
        )

        return Base64.encodeToString(
            encryptedBytes,
            Base64.NO_WRAP
        )
    }

    override fun decrypt(encryptedPassword: String): String {
        val privateKey = getKeyPair().private

        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, privateKey, OAEP_SPEC)

        val encryptedBytes = Base64.decode(
            encryptedPassword,
            Base64.NO_WRAP
        )

        return String(
            cipher.doFinal(encryptedBytes),
            Charsets.UTF_8
        )
    }

    private fun getKeyPair(): KeyPair {
        val privateKey = keyStore.getKey(
            KEY_ALIAS,
            null
        ) as PrivateKey

        val certificate = keyStore.getCertificate(KEY_ALIAS)
        val publicKey = certificate.publicKey

        return KeyPair(publicKey, privateKey)
    }

    private fun generateKeyPairIfNeeded() {
        if (keyStore.containsAlias(KEY_ALIAS)) return

        val keyPairGenerator = KeyPairGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_RSA,
            ANDROID_KEYSTORE
        )

        val parameterSpec = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or
                    KeyProperties.PURPOSE_DECRYPT
        )
            .setKeySize(2048)
            .setDigests(
                KeyProperties.DIGEST_SHA256,
                KeyProperties.DIGEST_SHA512
            )
            .setEncryptionPaddings(
                KeyProperties.ENCRYPTION_PADDING_RSA_OAEP
            )
            .build()

        keyPairGenerator.initialize(parameterSpec)
        keyPairGenerator.generateKeyPair()
    }

    companion object {
        private const val KEY_ALIAS = "pokemon_password_key"
        private const val ANDROID_KEYSTORE = "AndroidKeyStore"
        private const val TRANSFORMATION = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"
        private const val MD_NAME = "SHA-256"
        private const val MGF = "MGF1"
    }
}