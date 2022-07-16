import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import router from './router'
import * as Icons from '@element-plus/icons-vue'
import 'element-plus/dist/index.css'
import './assets/style/index.css'
import zhCn from 'element-plus/lib/locale/lang/zh-cn'
import App from './App.vue'

const app = createApp(App)
Object.keys(Icons).forEach(key => {
  app.component(key, Icons[key])
})
app.use(router).use(ElementPlus, {
  locale: zhCn
})
app.mount('#app')
