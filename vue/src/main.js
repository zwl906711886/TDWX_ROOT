import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
//导出
import Blob from "@/excel/Blob.js"
import Export2Excel from "@/excel/Export2Excel.js"

// import 'dayjs/locale/zh-cn'
// import locale from 'element-plus/lib/locale/lang/zh-cn'

import * as echarts from 'echarts'

import '@/assets/css/global.css'

const app = createApp(App)
    .use(store)
    .use(router)
    .use(ElementPlus, {size: 'small' })
    .use(Blob)
    .use(Export2Excel)
    .mount('#app')
app.echarts = echarts
