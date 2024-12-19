<script>
export default {
  name: "Login"
}
</script>

<template>
  <div>
    <h2>Login</h2>
    <input v-model="username" placeholder="Username" />
    <input v-model="password" type="password" placeholder="Password" />
    <button @click="doLogin">Login</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { loginUser } from '../api/auth.js'

const username = ref('')
const password = ref('')

async function doLogin() {
  try {
    const data = await loginUser({ username: username.value, password: password.value })
    // Предполагаем, что в ответе вы получаете токены. Например { accessToken: '...', refreshToken: '...' }
    localStorage.setItem('accessToken', data.accessToken)
    localStorage.setItem('refreshToken', data.refreshToken)
    alert('Logged in successfully!')
  } catch (error) {
    alert('Login error: ' + (error.response?.data || error.message))
  }
}
</script>
