import { createRouter, createWebHashHistory } from 'vue-router'
import ScheduleView from '@/views/ScheduleView.vue'

const routes = [
  {
    path: '/',
    name: 'schedule',
    component: ScheduleView
  },
  {
    path: '/team',
    name: 'team',
    component: function () {
      return import('@/views/TeamView.vue')
    }
  },
  {
    path: '/player',
    name: 'player',
    component: function () {
      return import('@/views/PlayerView.vue')
    }
  },
  {
    path: '/hero',
    name: 'hero',
    component: function () {
      return import('@/views/HeroView.vue')
    }
  },
  {
    path: '/404',
    name: 'pagenotfound',
    component: () => import("@/views/NotFound.vue")
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
