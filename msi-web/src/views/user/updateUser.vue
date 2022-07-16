<template>
  <el-dialog
    title="修改"
    v-model="isShow"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :before-close="handleClose"
  >
    <el-form
      ref="formRef"
      label-width="80px"
      v-loading="initLoading"
      :model="form"
      :rules="rules"
    >
      <el-form-item prop="username" label="用户名">
        <el-input v-model="form.username" :maxlength="30" />
      </el-form-item>
      <el-form-item prop="nickname" label="昵称">
        <el-input v-model="form.nickname" :maxlength="30" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="handleClose">取消</el-button>
      <el-button type="primary" @click="handleSubmit" :loading="submitLoading"
        >确定</el-button
      >
    </template>
  </el-dialog>
</template>

<script>
import { ref, reactive, toRefs, watch, getCurrentInstance } from 'vue'
import { getUser, updateUser } from '@/api/user'

export default {
  props: {
    visible: Boolean,
    id: {
      required: true,
      type: [Number, String]
    }
  },
  emits: ['closed', 'success'],
  setup(props, context) {
    const { proxy } = getCurrentInstance()
    const formRef = ref()
    const state = reactive({
      isShow: false,
      initLoading: true,
      submitLoading: false,
      form: {},
      rules: reactive({
        username: [
          {
            required: true,
            message: '请输入姓名',
            trigger: 'blur'
          },
          {
            min: 2,
            max: 30,
            message: '2至30个字符',
            trigger: 'blur'
          }
        ]
      })
    })

    const handleClose = done => {
      if (typeof done === 'function') {
        done()
      }
      formRef.value.resetFields()
      context.emit('closed')
    }
    const handleSubmit = () => {
      formRef.value.validate(valid => {
        if (valid) {
          state.submitLoading = true
          updateUser(props.id, state.form)
            .then(() => {
              context.emit('success')
              handleClose()
            })
            .catch(e => {
              proxy.$message({
                type: 'error',
                message: e.msg
              })
            })
            .finally(() => (state.submitLoading = false))
        }
      })
    }

    watch(
      () => props.visible,
      (newValue, oldValue) => {
        if (newValue) {
          state.initLoading = true
          state.submitLoading = true
          getUser(props.id)
            .then(res => (state.form = res.data))
            .finally(() => {
              state.initLoading = false
              state.submitLoading = false
            })
        }
        state.isShow = newValue
      }
    )

    return {
      formRef,
      ...toRefs(state),
      handleClose,
      handleSubmit
    }
  }
}
</script>
