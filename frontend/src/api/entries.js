import axios from 'axios'

export async function fetchEntries() {
    const response = await axios.get('/api/entries')
    return response.data
}

export async function fetchEntryById(id) {
    const response = await axios.get(`/api/entries/${id}`)
    return response.data
}

export async function createEntry(entry) {
    const response = await axios.post('/api/entries', entry)
    return response.data
}

export async function updateEntry(id, entry) {
    const response = await axios.put(`/api/entries/${id}`, entry)
    return response.data
}

export async function deleteEntry(id) {
    const response = await axios.delete(`/api/entries/${id}`)
    return response.data
}
