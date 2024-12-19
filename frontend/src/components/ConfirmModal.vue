<script>
export default {
  name: "ConfirmModal"
}
</script>

<template>
  <div v-if="show" class="modal-overlay">
    <div class="modal">
      <p>Are you sure you want to delete this entry?</p>
      <button @click="onConfirm" style="background:red;color:white;">Delete</button>
      <button @click="onCancel" style="margin-left:10px;">Cancel</button>
    </div>
  </div>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue'

const props = defineProps({
  show: { type: Boolean, default: false }
})

const emits = defineEmits(['confirm', 'cancel'])

function onConfirm() {
  emits('confirm')
}

function onCancel() {
  emits('cancel')
}
</script>

<style>
.modal-overlay {
  position: fixed; top:0; left:0; right:0; bottom:0;
  background: rgba(0,0,0,0.5);
  display:flex; align-items:center; justify-content:center;
}
.modal {
  background: #fff; padding: 20px; border-radius: 5px;
}
</style>