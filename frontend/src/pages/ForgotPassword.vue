<script>
export default {
  name: "ForgotPassword"
}
</script>

<template>
  <div>
    <h2>Forgot Password</h2>
    <input v-model="email" placeholder="Email" />
    <button @click="doForgotPassword">Send Reset Link</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { forgotPassword } from '../api/auth.js'

const email = ref('')

async function doForgotPassword() {
  try {
    const msg = await forgotPassword(email.value)
    alert(msg)
  } catch (error) {
    alert('Forgot password error: ' + (error.response?.data || error.message))
  }
}
</script>
