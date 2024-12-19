import axios from 'axios'

// Базовый URL может быть настроен в vite.config.js как прокси
// или напрямую здесь:
axios.defaults.baseURL = 'http://localhost:8082';

export async function registerUser(authRequest) {
    // authRequest: { username: string, password: string, ... }
    const response = await axios.post('/v1/auth/register', authRequest)
    return response.data
}

export async function loginUser(loginRequest) {
    // loginRequest: { username: string, password: string }
    const response = await axios.post('/v1/auth/login', loginRequest)
    return response.data
}

export async function refreshToken(refreshToken) {
    const params = new URLSearchParams();
    params.append('refreshToken', refreshToken);
    const response = await axios.post('/v1/auth/refresh-token', params)
    return response.data
}

export async function forgotPassword(email) {
    // Отправляем запрос вида /v1/auth/forgot-password?email=...
    const response = await axios.post(`/v1/auth/forgot-password?email=${encodeURIComponent(email)}`)
    return response.data
}

export async function resetPassword(resetRequest) {
    // resetRequest: { token: string, newPassword: string }
    const response = await axios.post('/v1/auth/reset-password', resetRequest)
    return response.data
}
