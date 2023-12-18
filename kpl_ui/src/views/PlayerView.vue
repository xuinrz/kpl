<template>
  <div class="tablespace">
    <a-table class="atable" :columns="columns" :data-source="results" :scroll="{ y: 'calc(80vh - 20px)' }"
      :pagination="false" :showSorterTooltip="false" size="small" :loading=this.isloading>
      <template #bodyCell="{ column, index, record }">
        <template v-if="column.dataIndex === 'rank'">
          <p class="rank">
            {{ index + 1 }}
          </p>
        </template>
        <template v-else-if="column.dataIndex === 'player_name'">
          <img :src=record.player_icon /> {{ record.player_name }}
        </template>
      </template>
    </a-table>
  </div>
</template>

<script>
import config from '@/config.js'

export default {
  data() {
    return {
      isloading: true,
      columns: [
        {
          title: '排名',
          dataIndex: 'rank',
          fixed: 'left',
          width: 90
        },
        {
          title: '选手',
          dataIndex: 'player_name',
          fixed: 'left',
          width: 170
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
          title: '经济占比',
          dataIndex: 'avg_gold_rate',
          sorter: (a, b) => a.avg_gold_rate - b.avg_gold_rate,
          width: 90
        },
        {
          title: '场均伤害',
          dataIndex: 'avg_hurt_to_hero_total',
          sorter: (a, b) => a.avg_hurt_to_hero_total - b.avg_hurt_to_hero_total,
          width: 90
        },
        {
          title: '分均伤害',
          dataIndex: 'avg_per_min_hurt_to_hero_total',
          sorter: (a, b) => a.avg_per_min_hurt_to_hero_total - b.avg_per_min_hurt_to_hero_total,
          width: 90
        },
        {
          title: '伤害占比',
          dataIndex: 'avg_hurt_to_hero_total_rate',
          sorter: (a, b) => a.avg_hurt_to_hero_total_rate - b.avg_hurt_to_hero_total_rate,
          width: 90
        },
        {
          title: '伤害转化率',
          dataIndex: 'avg_damage_convert_rate',
          sorter: (a, b) => a.avg_damage_convert_rate - b.avg_damage_convert_rate,
          width: 110
        },
        {
          title: '场均承伤',
          dataIndex: 'avg_be_hurt_total',
          sorter: (a, b) => a.avg_be_hurt_total - b.avg_be_hurt_total,
          width: 90
        },
        {
          title: '分均承伤',
          dataIndex: 'avg_per_min_be_hurt_by_hero_total',
          sorter: (a, b) => a.avg_per_min_be_hurt_by_hero_total - b.avg_per_min_be_hurt_by_hero_total,
          width: 90
        },
        {
          title: '承伤占比',
          dataIndex: 'avg_be_hurt_by_hero_total_rate',
          sorter: (a, b) => a.avg_be_hurt_by_hero_total_rate - b.avg_be_hurt_by_hero_total_rate,
          width: 90
        },
        {
          title: '场均推塔',
          dataIndex: 'avg_push_tower_num',
          sorter: (a, b) => a.avg_push_tower_num - b.avg_push_tower_num,
          width: 90
        },
        {
          title: '推塔占比',
          dataIndex: 'avg_push_tower_rate',
          sorter: (a, b) => a.avg_push_tower_rate - b.avg_push_tower_rate,
          width: 90
        },
        {
          title: '参团率',
          dataIndex: 'avg_participation_rate',
          sorter: (a, b) => a.avg_participation_rate - b.avg_participation_rate,
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
      a: []
    }
  },
  mounted() {

    this.$http.get(config.burl + "/player").then(
      (res) => {
        this.a = res.data.data;
        for (let i = 0; i < this.a.length; i++) {
          this.results[i] = Object.assign({}, this.a[i].statistics_info, this.a[i].player_info)
        }
      }
    )
    this.isloading=false;
  },
  

};
</script>

<style scoped>
.tablespace {
  margin: 30px 10% 0 10%;
  min-width: 700px;
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
}</style>

