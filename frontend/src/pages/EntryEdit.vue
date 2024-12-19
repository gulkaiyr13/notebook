<script>
export default {
  name: "EntryEdit"
}
</script>

<template>
  <div>
    <h1>{{ isNew ? 'Create note' : 'Edit note' }}</h1>
    <div style="margin-bottom:10px;">
      <input v-model="title" placeholder="Title" style="width:300px; padding:5px;" />
    </div>
    <QuillEditor v-model="content" style="height:200px;" />
    <div style="margin-top:10px;">
      <button @click="saveEntry">Save</button>
      <button @click="goBack" style="margin-left:10px;">Cancel</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { QuillEditor } from '@vueup/vue-quill'
import { createEntry, updateEntry, fetchEntryById } from '../api/entries.js'

const route = useRoute()
const router = useRouter()

const isNew = route.params.id === undefined || route.params.id === 'new'
const title = ref('')
const content = ref('')

onMounted(async () => {
  if (!isNew) {
    try {
      const data = await fetchEntryById(route.params.id)
      title.value = data.title
      content.value = data.content
    } catch (e) {
      alert('Error creating note.')
      router.push('/')
    }
  }
})

async function saveEntry() {
  if (!title.value.trim() || !content.value.trim()) {
    alert('Title and constraints cannot be empty.')
    return
  }
  if (content.value.length > 10000) {
    alert('Maximum entry length of 10,000 characters exceeded.')
    return
  }

  const entryData = {
    title: title.value.trim(),
    content: content.value.trim()
  }

  try {
    if (isNew) {
      await createEntry(entryData)
    } else {
      await updateEntry(route.params.id, entryData)
    }
    router.push('/')
  } catch (e) {
    if (e.response && e.response.status === 409) {
      alert('A post with the same title already exists.')
    } else {
      alert('Error saving entry.')
    }
  }
}

function goBack() {
  router.push('/')
}
</script>
