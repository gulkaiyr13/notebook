<script setup>
// import HelloWorld from './components/HelloWorld.vue'
</script>

<template>
  <div>
    <nav style="margin-bottom:20px;">
      <router-link to="/register"style="margin-left:10px;">Register</router-link>
      <router-link to="/login"style="margin-left:10px;">Login</router-link>
      <router-link to="/notes"style="margin-left:10px;">List of notes</router-link>
      <router-link to="/entry/new" style="margin-left:10px;">Create note</router-link>
    </nav>
    <router-view></router-view>
  </div>
</template>


<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
