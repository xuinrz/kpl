<template>
  <div class="schedulelist">
    <a-list item-layout="horizontal" :data-source="results" :scroll="{ y: 'calc(80vh - 20px)' }">
      <template #renderItem="{ item, index }">
        <a-list-item @click="console.log('hihihi')" :class="{ win: item.select }">
          <a-list-item-meta>
            <template #title>
              <span class="date" v-if="index==0||notSameDay(index)">{{ formatDate(item.start_time) }}</span>
              <br v-if="index==0||notSameDay(index)"/>
              <br v-if="index==0||notSameDay(index)"/>
              <img :src=item.camp1.team_icon>
              <span class="tname">{{ item.camp1.team_name }}</span>
              <span class="score" :class="{ win: item.camp1.is_win }">{{ item.camp1.score }}</span>
              <br /><br />
              <img :src=item.camp2.team_icon>
              <span class="tname">{{ item.camp2.team_name }}</span>
              <span class="score" :class="{ win: item.camp2.is_win }">{{ item.camp2.score }}</span>
              <br />
            </template>
          </a-list-item-meta>
        </a-list-item>
      </template>
      <template #header>
        <div class="title">王者荣耀职业联赛2023年夏季赛赛程</div>
      </template>
    </a-list>
  </div>
</template>
<script>
import config from '@/config';

export default {

  data() {
    return {
      results: [],
    };
  },
  mounted() {

    this.$http.get(config.burl + "/schedule").then(
      (res) => {
        this.results = res.data.data;
      }
    )
  },
  methods: {
    notSameDay(i){
      return !(this.formatDate(this.results[i].start_time)==this.formatDate(this.results[i-1].start_time));
    },
    formatDate(dateString) {
      const inputDate = new Date(dateString);
      const year = inputDate.getFullYear();
      const month = inputDate.getMonth() + 1;
      const day = inputDate.getDate();
      const formattedDateTime = `${year}年 ${month}月${day}日`;
      return formattedDateTime;
    }

  }


};
</script>

<style scoped>
.schedulelist {
  margin: 30px 40px 0 120px;
  width: 17%;
  min-width: 300px;
  box-shadow: 0 0 10px 5px rgba(0, 0, 0, 0.2);
  height: calc(82vh);
  overflow-y: scroll;
  float: left;
}

.schedulelist img {
  width: 36px;
  float: left;
  margin: 0 20px 0 10px;
  border-radius: 20%;
}

.tname {
  font-size: larger;
  float: left;
  font: bold;
  margin-top: 5px;
}

.score {
  font-size: 22px;
  float: right;
  font: bold;
  margin-right: 10px;
}

.true {
  color: gold;
}

.date {
  padding-left: 14px;
  font-size: large;
  font: bold;
  float: left;
}

.ant-list-split .ant-list-item {
  border-block-end: 2px solid rgba(5, 5, 5, 0.1);
}

.win {
  color: rgb(255, 223, 40);
}

.title {
  font: bolder;
}
</style>