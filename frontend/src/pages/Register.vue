<script>
export default {
  name: "Register"
}
</script>

<template>
  <div>
    <h2>Register</h2>
    <input v-model="username" placeholder="Username" />
    <input v-model="password" type="password" placeholder="Password" />
    <button @click="doRegister">Register</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { registerUser } from '../api/auth.js'

const username = ref('')
const password = ref('')

async function doRegister() {
  try {
    const data = await registerUser({ username: username.value, password: password.value })
    alert('Registered successfully: ' + JSON.stringify(data))
  } catch (error) {
    alert('Registration error: ' + error.response?.data || error.message)
  }
}
</script>