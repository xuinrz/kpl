<template>
  <div class="schedule">
    <Schedule/>
  </div>
</template>

<script>
import Schedule from '@/components/ScheduleList.vue'
import config from '@/config.js'

export default {
  name: 'ScheduleView',
  components: {
    Schedule,
  },

  data() {
    return {
      schedule: [],
    }
  },

  mounted() {

    this.$http.get(config.burl+"/schedule").then(
      (res)=>{
        this.schedule=res.data.data;
      }
    )

  }
}
</script>
