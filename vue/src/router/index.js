import {createRouter, createWebHistory} from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
    {
        path: '/',
        name: 'Layout',
        component: Layout,
        redirect: "/home",
        children: [
            {
                path: 'home',
                name: 'Home',
                component: () => import("@/views/Home"),
            },
            {
                path: 'user',
                name: 'User',
                component: () => import("@/views/User"),
            },
            {
                path: 'news',
                name: 'News',
                component: () => import("@/views/News"),
            },
            {
                path: 'person',
                name: 'Person',
                component: () => import("@/views/Person"),
            },

            {
                path: 'message',
                name: 'Message',
                component: () => import("@/views/Message"),
            },
            {
                path: 'open',
                name: 'Open',
                component: () => import("@/views/Open")
            },
            {
                path: 'major',
                name: 'Major',
                component: () => import("@/views/Major")
            },
            {
                path: 'batch',
                name: 'Batch',
                component: () => import("@/views/Batch")
            },
            {
                path: 'enroll',
                name: 'Enroll',
                component: () => import("@/views/Enroll")
            },
            {
                path: 'enrollTable',
                name: 'EnrollTable',
                component: () => import("@/views/EnrollTable")
            },{
                path: 'question',
                name: 'Question',
                component: () => import("@/views/Question")
            }
        ]
    },
    {
        path: '/about',
        name: 'about',
        component: () => import("@/views/User")
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import("@/views/Login")
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import("@/views/Register")
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

// 限制某些页面禁止未登录访问
let limitPagePath = ['/about']

router.beforeEach((to, from, next) => {
    if (limitPagePath.includes(to.path)) {
        // 判断sessionStorage是否保存了用户信息
        let userStr = sessionStorage.getItem("user") || "{}"
        let user = JSON.parse(userStr)
        if (!user.id) {
            // 跳转到登录页面
            next({path: "/login"})
        } else {
            next()
        }
    } else {
        next()
    }
})

export default router
