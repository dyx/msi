<template>
  <el-dialog
    :title="form.username ? '用户：' + form.username : ''"
    v-model="isShow"
    :before-close="handleClose"
  >
    <el-descriptions border :column="2" v-loading="initLoading">
      <el-descriptions-item label="用户名" label-align="right" width="180px">{{
        form.username
      }}</el-descriptions-item>
      <el-descriptions-item label="昵称" label-align="right">{{
        form.nickname
      }}</el-descriptions-item>
      <el-descriptions-item label="创建时间" label-align="right">{{
        form.createTime
      }}</el-descriptions-item>
      <el-descriptions-item label="修改时间" label-align="right">{{
        form.updateTime
      }}</el-descriptions-item>
    </el-descriptions>
  </el-dialog>
</template>

<script>
import { ref, reactive, toRefs, watch } from 'vue'
import { getUser } from '@/api/user'

export default {
  props: {
    visible: Boolean,
    id: {
      required: true,
      type: [Number, String]
    }
  },
  emits: ['closed'],
  setup(props, context) {
    const formRef = ref()
    const state = reactive({
      isShow: false,
      initLoading: true,
      form: {}
    })

    const handleClose = done => {
      if (typeof done === 'function') {
        done()
      }
      state.form = {}
      context.emit('closed')
    }

    watch(
      () => props.visible,
      (newVisible, oldVisible) => {
        if (newVisible) {
          state.initLoading = true
          getUser(props.id)
            .then(res => (state.form = res.data))
            .finally(() => {
              state.initLoading = false
            })
        }
        state.isShow = newVisible
      }
    )

    return {
      formRef,
      ...toRefs(state),
      handleClose
    }
  }
}
</script>
