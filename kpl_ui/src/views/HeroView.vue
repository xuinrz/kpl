<template>
  <div class="layout">
    <div>
      <div id="winratepie" class="charts"></div>
      <div id="pickratepie" class="charts"></div>
    </div>
    <div class="tablespace">
      <a-table class="atable" :columns="columns" :data-source="results" :scroll="{ y: 'calc(80vh - 20px)' }"
        :pagination="false" :showSorterTooltip="false" size="small">
        <template #bodyCell="{ column, index, record }">
          <template v-if="column.dataIndex === 'rank'">
            <p class="rank">
              {{ index + 1 }}
            </p>
          </template>
          <template v-else-if="column.dataIndex === 'hero_name'">
            <img :src=record.hero_icon /> {{ record.hero_name }}
          </template>
        </template>
      </a-table>
    </div>
  </div>
</template>

<script>
import config from '@/config.js'

import * as echarts from 'echarts';

import "echarts-wordcloud";


export default {
  data() {
    return {
      columns: [
        {
          title: '排名',
          dataIndex: 'rank',
          fixed: 'left',
          width: 90
        },
        {
          title: '英雄',
          dataIndex: 'hero_name',
          fixed: 'left',
          width: 100
        },
        {
          title: '出场次数',
          dataIndex: 'pick_num',
          sorter: (a, b) => a.pick_num - b.pick_num,
          width: 90
        },
        {
          title: '出场率',
          dataIndex: 'pick_rate',
          sorter: (a, b) => a.pick_rate - b.pick_rate,
          width: 90
        },
        {
          title: '禁用率',
          dataIndex: 'ban_rate',
          sorter: (a, b) => a.ban_rate - b.ban_rate,
          width: 90
        },
        {
          title: '胜场次数',
          dataIndex: 'victory_battle_count',
          sorter: (a, b) => a.victory_battle_count - b.victory_battle_count,
          width: 90
        },
        {
          title: '胜率',
          dataIndex: 'win_rate',
          sorter: (a, b) => a.win_rate - b.win_rate,
          width: 90
        },
        {
          title: '场均击杀',
          dataIndex: 'avg_kill_num',
          sorter: (a, b) => a.avg_kill_num - b.avg_kill_num,
          width: 90
        },
        {
          title: '场均死亡',
          dataIndex: 'avg_death_num',
          sorter: (a, b) => a.avg_death_num - b.avg_death_num,
          width: 90
        },
        {
          title: '场均助攻',
          dataIndex: 'avg_assist_num',
          sorter: (a, b) => a.avg_assist_num - b.avg_assist_num,
          width: 90
        },
        {
          title: '场均时长',
          dataIndex: 'avg_game_duration',
          sorter: (a, b) => a.avg_game_duration - b.avg_game_duration,
          width: 90
        },

      ],
      results: [],
      a: [],
      win_rate: [],
      pick_rate: [{ value: 0, name: "" }],
    }
  },
  mounted() {

    this.$http.get(config.burl + "/hero").then(
      (res) => {
        this.a = res.data.data;
        for (let i = 0; i < this.a.length; i++) {
          this.results[i] = Object.assign({}, this.a[i].statistics_info, this.a[i].hero_info)
          this.results[i] = Object.assign({}, this.a[i].bp_statistics_info, this.results[i])
        }
        this.draw_win_rate();
        this.draw_pick_rate();
      }
    );




  },
  methods: {
    win_rate_data() {
      var res = this.results;
      const filtered = res.filter(item => item.pick_num > 10);
      var tmp = filtered.sort((a, b) => { return b.win_rate - a.win_rate });
      for (var i = 0; i < 30; i++) {
        this.win_rate[i] = {value:tmp[i].win_rate,name:tmp[i].hero_name}
      }
    },
    draw_win_rate() {
      this.win_rate_data();
      const myChart = echarts.init(document.getElementById("winratepie"));// 图标初始化
      myChart.setOption({
        title: {
          text: "胜率词云图"
        },
        tooltip: {},
        series: [
          {
            type: "wordCloud",
            sizeRange: [12, 60],
            rotationRange: [-90, 90],
            rotationStep: 45,
            gridSize: 15,
            shape: "pentagon",
            width: "100%",
            height: "100%",
            drawOutOfBound: true,
            textStyle: {
              color: function () {
                return (
                  "rgb(" +
                  [
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160),
                  ].join(",") +
                  ")"
                );
              },
            },
            emphasis: {
              textStyle: {
                shadowBlur: 10,
                shadowColor: "#333",
                color: "red",
              },
            },
            data: this.win_rate
          },
        ],
      });
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
    pick_rate_data() {
      var res = this.results;
      var tmp = res.sort((a, b) => { return b.pick_rate - a.pick_rate }).slice(0, 40);
      for (var i = 0; i < 40; i++) {
        this.pick_rate[i] = { value: tmp[i].pick_rate, name: tmp[i].hero_name };
      }
    },
    draw_pick_rate() {
      this.pick_rate_data();
      const myChart = echarts.init(document.getElementById("pickratepie"));// 图标初始化
      myChart.setOption({
        tooltip: {
          trigger: 'item'
        },
        title: {
          text: "登场率"
        },
        series: [
          {
            name: '登场率',
            type: 'pie',
            radius: ['25%', '55%'],
            itemStyle: {
              borderColor: '#fff',
              borderWidth: 1
            },
            label: {
              show: true,
              position: 'outer',
              edgeDistance: '5%',
              distanceToLabelLine: '0'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 30,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: this.pick_rate
          }
        ]
      });
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    }
  }

};
</script>

<style scoped>
.layout {
  display: flex;
}

#winratepie {
  height: 240px;
  margin-bottom: 50px;
}

.charts {
  width: 400px;
  margin: 30px 20px 0 20px;
  height: 380px;
}


.tablespace {
  width: 65%;
  min-width: 700px;
  margin: 30px 20px 30px 20px;
  box-shadow: 0 0 10px 5px rgba(0, 0, 0, 0.2);
}

.rank {
  text-align: center;
  padding-top: 20px;
  font-size: 20px;
  font-weight: bolder;
  color: rgb(83, 192, 235);
}

img {
  width: 38px;
  border-radius: 50%;
}
</style>

