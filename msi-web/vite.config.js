import { defineConfig, loadEnv } from 'vite'
import path from 'path'
import vue from '@vitejs/plugin-vue'
import eslintPlugin from 'vite-plugin-eslint'

// https://cn.vitejs.dev/config
export default defineConfig(mode => {
  console.log(process)
  return {
    // https://cn.vitejs.dev/config/shared-options.html#plugins
    plugins: [vue(), eslintPlugin({ cache: false })],
    // https://cn.vitejs.dev/config/shared-options.html#resolve-alias
    resolve: {
      alias: {
        '@': path.resolve(__dirname, './src')
      },
      extensions: ['.js']
    },
    // https://cn.vitejs.dev/config/server-options.html
    server: {
      port: 8002,
      host: true,
      proxy: {
        '/api': {
          target: 'http://localhost:8001',
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, '')
        }
      }
    }
  }
})
