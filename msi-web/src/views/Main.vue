<template>
  <el-container>
    <el-aside width="150px" style="height: 100%">
      <el-menu router :default-active="currentRoutePath" :collapse="isCollapse">
        <el-menu-item
          v-for="(item, index) in menuData"
          :key="index"
          :index="item.path"
          :route="item.path"
        >
          <el-icon>
            <component :is="item.icon" />
          </el-icon>
          {{ item.name }}
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header height="40px" style="text-align: right; font-size: 12px">
        <el-avatar :size="30" style="margin-top: 5px">
          <template #default>
            <el-icon>
              <user-filled />
            </el-icon>
          </template>
        </el-avatar>
      </el-header>
      <el-divider></el-divider>
      <el-main>
        <el-scrollbar>
          <router-view />
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { reactive, toRefs, watch } from 'vue'
import { useRouter } from 'vue-router'

export default {
  setup() {
    const router = useRouter()
    const state = reactive({
      isCollapse: false,
      currentRoutePath: router.currentRoute.value.path,
      menuData: [
        {
          path: '/user',
          name: '用户管理',
          icon: 'user'
        },
        {
          path: '/test',
          name: '测试页',
          icon: 'user'
        }
      ]
    })

    watch(
      () => router.currentRoute.value.path,
      (newValue, oldValue) => {
        state.currentRoutePath = newValue
      }
    )

    return {
      ...toRefs(state)
    }
  }
}
</script>

<style scoped>
:deep(.el-divider--horizontal) {
  margin: 5px 0;
}

:deep(.el-menu) {
  height: 100%;
}
</style>
