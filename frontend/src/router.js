import { createRouter, createWebHistory } from 'vue-router'
import EntryList from './pages/EntryList.vue'
import EntryEdit from './pages/EntryEdit.vue'
import Register from './pages/Register.vue'
import Login from './pages/Login.vue'
import ForgotPassword from './pages/ForgotPassword.vue'
import ResetPassword from './pages/ResetPassword.vue'
import RefreshToken from './pages/RefreshToken.vue'

const routes = [
    { path: '/register', component: Register },
    { path: '/login', component: Login },
    { path: '/forgot', component: ForgotPassword },
    { path: '/reset', component: ResetPassword },
    { path: '/refresh', component: RefreshToken },
    { path: '/', component: EntryList, name: 'home' },
    { path: '/entry/new', component: EntryEdit, name: 'newEntry' },
    { path: '/entry/:id', component: EntryEdit, name: 'editEntry' }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
