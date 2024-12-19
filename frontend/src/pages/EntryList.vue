<script>
export default {
  name: "EntryList"
}
</script>

<template>
  <div>
    <h1>My notes</h1>
    <ul>
      <li v-for="entry in entries" :key="entry.id">
        <router-link :to="`/entry/${entry.id}`">{{ entry.title }}</router-link>
        <button @click="deletePrompt(entry.id)" style="margin-left:10px;">Delete</button>
      </li>
    </ul>

    <ConfirmModal :show="confirmVisible" @confirm="deleteConfirmed" @cancel="confirmVisible = false" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { fetchEntries, deleteEntry } from '../api/entries.js'
import ConfirmModal from '../components/ConfirmModal.vue'

const entries = ref([])
const confirmVisible = ref(false)
let entryToDelete = null

onMounted(async () => {
  entries.value = await fetchEntries()
})

function deletePrompt(id) {
  entryToDelete = id
  confirmVisible.value = true
}

async function deleteConfirmed() {
  if (entryToDelete) {
    try {
      await deleteEntry(entryToDelete)
      entries.value = entries.value.filter(e => e.id !== entryToDelete)
    } catch (e) {
      alert('Error deleting entry.')
    } finally {
      confirmVisible.value = false
    }
  }
}
</script>