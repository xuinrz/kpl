<template>
  <div class="layout">
    <div>
      <div id="winratepie" class="charts"></div>
      <div id="radar" class="charts"></div>
    </div>
    <div class="tablespace">
      <a-table class="atable" :columns="columns" :data-source="results" :scroll="{ y: 'calc(80vh - 20px)' }"
        :pagination="false" :showSorterTooltip="false" size="small">
        <template #bodyCell="{ column, index, record }">
          <div v-if="column.dataIndex === 'rank'" @click="draw_radar(index)">
            <p class="rank">
              {{ index + 1 }}
            </p>
          </div>
          <div v-else-if="column.dataIndex === 'team_name'" @click="draw_radar(index)">
            <img :src=record.team_icon /> {{ record.team_name }}
          </div>
        </template>
      </a-table>
    </div>
  </div>
</template>

<script>
import config from '@/config.js'
import * as echarts from 'echarts';
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
          title: '战队',
          dataIndex: 'team_name',
          fixed: 'left',
          width: 150
        },
        {
          title: '比赛场次',
          dataIndex: 'battle_count',
          sorter: (a, b) => a.battle_count - b.battle_count,
          width: 90
        },
        {
          title: '胜场',
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
          title: '场均KDA',
          dataIndex: 'avg_kda',
          sorter: (a, b) => a.avg_kda - b.avg_kda,
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
          title: '场均经济',
          dataIndex: 'avg_gold',
          sorter: (a, b) => a.avg_gold - b.avg_gold,
          width: 90
        },
        {
          title: '分均经济',
          dataIndex: 'avg_gpm',
          sorter: (a, b) => a.avg_gpm - b.avg_gpm,
          width: 90
        },
        {
          title: '场均伤害',
          dataIndex: 'avg_hurt_total',
          sorter: (a, b) => a.avg_hurt_total - b.avg_hurt_total,
          width: 90
        },
        {
          title: '分均伤害',
          dataIndex: 'avg_per_min_hurt_total',
          sorter: (a, b) => a.avg_per_min_hurt_total - b.avg_per_min_hurt_total,
          width: 90
        },
        {
          title: '场均承伤',
          dataIndex: 'avg_be_hurt_total',
          sorter: (a, b) => a.avg_be_hurt_total - b.avg_be_hurt_total,
          width: 90
        },
        {
          title: '分均承伤',
          dataIndex: 'avg_per_min_be_hurt_total',
          sorter: (a, b) => a.avg_per_min_be_hurt_total - b.avg_per_min_be_hurt_total,
          width: 90
        },
        {
          title: '一血率',
          dataIndex: 'avg_first_blood_cnt',
          sorter: (a, b) => a.avg_first_blood_cnt - b.avg_first_blood_cnt,
          width: 90
        },
        {
          title: '场均推塔',
          dataIndex: 'avg_push_tower_num',
          sorter: (a, b) => a.avg_push_tower_num - b.avg_push_tower_num,
          width: 90
        },
        {
          title: '场均被推',
          dataIndex: 'avg_other_camp_push_tower_num',
          sorter: (a, b) => a.avg_other_camp_push_tower_num - b.avg_other_camp_push_tower_num
          ,
          width: 90
        },
        {
          title: '场均暴君',
          dataIndex: 'avg_kill_all_tyrant_num',
          sorter: (a, b) => a.avg_kill_all_tyrant_num - b.avg_kill_all_tyrant_num,
          width: 90
        },
        {
          title: '暴君控率',
          dataIndex: 'avg_kill_all_tyrant_control_rate',
          sorter: (a, b) => a.avg_kill_all_tyrant_control_rate - b.avg_kill_all_tyrant_control_rate
          ,
          width: 90
        },
        {
          title: '场均主宰',
          dataIndex: 'avg_kill_all_dragon_num',
          sorter: (a, b) => a.avg_kill_all_dragon_num - b.avg_kill_all_dragon_num,
          width: 90
        },
        {
          title: '主宰控率',
          dataIndex: 'avg_kill_all_dragon_control_rate',
          sorter: (a, b) => a.avg_kill_all_dragon_control_rate - b.avg_kill_all_dragon_control_rate
          ,
          width: 90
        },
        {
          title: '场均龙王',
          dataIndex: 'avg_kill_storm_dragon_king_num',
          sorter: (a, b) => a.avg_kill_storm_dragon_king_num - b.avg_kill_storm_dragon_king_num
          ,
          width: 90
        },
        {
          title: '龙王控率',
          dataIndex: 'avg_kill_storm_dragon_king_control_rate',
          sorter: (a, b) => a.avg_kill_storm_dragon_king_control_rate - b.avg_kill_storm_dragon_king_control_rate
          ,
          width: 90
        },
        {
          title: '场均时长',
          dataIndex: 'avg_game_duration',
          sorter: (a, b) => a.avg_game_duration - b.avg_game_duration
          ,
          width: 90
        },

      ],
      results: [],
      a: [],
      win_rate_value: [],
      win_rate_name: [],
      st: '',
      radar1: [],
      radar2: [],
      radarodd: true,
      name1: '',
      name2: '',

    }
  },
  mounted() {
    this.$http.get(config.burl + "/team").then(
      (res) => {
        this.a = res.data.data;
        for (let i = 0; i < this.a.length; i++) {
          this.results[i] = Object.assign({}, this.a[i].statistics_info, this.a[i].team_info)
        }
        this.draw_win_rate();
        this.draw_radar(0);
      }
    )
  },
  methods: {
    win_rate_data() {
      var res = this.results;
      var tmp = res.sort((a, b) => { return b.win_rate - a.win_rate });
      for (var i = 0; i < 5; i++) {
        this.win_rate_value[i] = tmp[i].win_rate;
        this.win_rate_name[i] = tmp[i].team_abbreviation;
      }
    },
    draw_win_rate() {
      this.win_rate_data();
      const myChart = echarts.init(document.getElementById("winratepie"));// 图标初始化
      myChart.setOption({
        title: {
          text: "战队胜率榜"
        },
        tooltip: {},
        legend: {
          data: ["胜率"]
        },
        xAxis: {
          type: 'category',
          data: this.win_rate_name
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: "胜率",
            type: "bar",
            data: this.win_rate_value,
          }
        ]
      });
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
    radar_data(index) {
      const a = this.results[index];

      if (this.radarodd) {
        this.radar1 = [a.avg_gpm, a.avg_kda, a.avg_per_min_hurt_total, a.avg_push_tower_num, a.avg_kill_all_tyrant_control_rate + a.avg_kill_all_dragon_control_rate, a.avg_first_blood_cnt];
        this.name1 = a.team_name;
      }
      if (!this.radarodd) {
        this.radar2 = [a.avg_gpm, a.avg_kda, a.avg_per_min_hurt_total, a.avg_push_tower_num, a.avg_kill_all_tyrant_control_rate + a.avg_kill_all_dragon_control_rate, a.avg_first_blood_cnt];
        this.name2 = a.team_name;
      }
      this.radarodd = !(this.radarodd);
      console.log(this.radar1)
    },
    draw_radar(index) {
      this.radar_data(index);
      const myChart = echarts.init(document.getElementById("radar"));// 图标初始化
      myChart.setOption({
        title: {
          text: '战队能力图'
        },
        legend: {
        },
        tooltip: {},
        radar: {
          alignTicks: false,
          shape: 'circle',
          indicator: [
            { name: '分均经济', max: 3500 },
            { name: 'KDA', max: 10 },
            { name: '分均伤害', max: 120000 },
            { name: '场均推塔', max: 5 },
            { name: '场均控龙', max: 1.3 },
            { name: '一血率', max: 0.65 }
          ]
        },
        series: [
          {
            name: this.name1,
            type: 'radar',
            data: [
              {
                value: this.radar1
              },
            ],

            itemStyle: {
              color: '#4175F5',
            },
          },
          {
            name: this.name2,
            type: 'radar',
            data: [
              {
                value: this.radar2
              },
            ],

            itemStyle: {
              color: '#ea7500',
            },
          }
        ]
      });
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
  }
};
</script>

<style scoped>
.layout {
  display: flex;
}

#winratepie {
  height: 300px;
}

.charts {
  width: 400px;
  margin: 30px 20px 0 20px;
  height: 320px;
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
  width: 40px;
  border-radius: 20%;
}
</style>

