<script>
export default {
  name: "RefreshToken"
}
</script>

<template>
  <div>
    <h2>Refresh Token</h2>
    <button @click="doRefresh">Refresh Access Token</button>
  </div>
</template>

<script setup>
import { refreshToken } from '../api/auth.js'

async function doRefresh() {
  const storedRefreshToken = localStorage.getItem('refreshToken')
  if (!storedRefreshToken) {
    alert('No refresh token found!')
    return
  }

  try {
    const data = await refreshToken(storedRefreshToken)
    // Предположим ответ также отдает новый accessToken
    localStorage.setItem('accessToken', data.accessToken)
    alert('Access token refreshed.')
  } catch (error) {
    alert('Refresh error: ' + (error.response?.data || error.message))
  }
}
</script>
