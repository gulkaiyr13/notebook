<script>
export default {
  name: "ResetPassowrd"
}
</script>

<template>
  <div>
    <h2>Reset Password</h2>
    <!-- Предполагаем, что на почту выслали ссылку вида: /reset?token=...
         и вы этот токен получили, к примеру, через query-параметры роутера. -->
    <input v-model="token" placeholder="Reset Token" />
    <input v-model="newPassword" type="password" placeholder="New Password" />
    <button @click="doResetPassword">Reset Password</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { resetPassword } from '../api/auth.js'
import { useRoute } from 'vue-router'

const route = useRoute()

const token = ref(route.query.token || '')
const newPassword = ref('')

async function doResetPassword() {
  try {
    const msg = await resetPassword({ token: token.value, newPassword: newPassword.value })
    alert(msg)
  } catch (error) {
    alert('Reset password error: ' + (error.response?.data || error.message))
  }
}
</script>
