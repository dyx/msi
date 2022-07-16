import { createRouter, createWebHashHistory } from 'vue-router'
import Main from '@/views/Main.vue'

const routes = [
  {
    path: '/',
    redirect: '/user',
    component: Main,
    children: [
      {
        path: '/user',
        component: () => import('@/views/user/index.vue')
      },
      {
        path: '/test',
        component: () => import('@/Test.vue')
      }
    ]
  }
]

// https://router.vuejs.org/zh/guide/index.html#javascript
const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
