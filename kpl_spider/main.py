import requests
import json

head = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) "
                  "Chrome/58.0.3029.110 Safari/537.36"
}


# 脚本功能：
# 1.获取一个赛季的赛程
# 2.获取一场比赛的信息（比赛双方、对战场次）
# 3.获取一局对战的信息（bp、选手、胜败方、队伍战绩、选手战绩）
# 4.下载三个“数据”模块的表格数据


# 1.获取一个赛季的赛程
def scheduleInfo(league):
    url = 'https://prod.comp.smoba.qq.com/leaguesite/matches/open?league_id=' + league
    response = requests.get(url, headers=head)
    # 检查响应是否成功
    if response.status_code == 200:
        # 打印网页内容
        pretty_json = pretty(response.json()['results'])
        with open('赛程信息json.txt', 'w', encoding='utf-8') as file:
            file.write(pretty_json)
        # 赛程安排matches（所有比赛）
        matches = response.json()['results']
        # 循环获取每场比赛的信息
        for match in matches:
            match_id = match['match_id']
            matchInfo(match_id)
    else:
        print("赛程信息请求失败，状态码：", response.status_code)


# 2.获取一场比赛的信息（比赛双方、对战场次）
def matchInfo(match_id):
    url = 'https://prod.comp.smoba.qq.com/leaguesite/match/battles/open?match_id=' + match_id
    response = requests.get(url, headers=head)
    # 检查响应是否成功
    if response.status_code == 200:
        dic = response.json()
        dic.update({"match_id": match_id})
        pretty_json = pretty(dic)
        with open('比赛信息json.txt', 'a', encoding='utf-8') as file:
            file.write(pretty_json)
            # 一场比赛包含的对局（比如BO5可能有5局）
            battles = response.json()['results']
            # 循环获取每局对局的信息
            for battle in battles:
                battle_id = battle['battle_id']
                battleInfo(battle_id)
    else:
        print("比赛信息请求失败，状态码：", response.status_code)


# 3.获取一局对战的信息（bp、选手、胜败方、队伍战绩、选手战绩）
def battleInfo(battle_id):
    url = 'https://prod.comp.smoba.qq.com/leaguesite/battle/open?battle_id=' + battle_id
    response = requests.get(url, headers=head)
    # 检查响应是否成功
    if response.status_code == 200:
        # 打印网页内容
        dic = response.json()
        dic.update({"battle_id": battle_id})
        pretty_json = pretty(dic)
        print(dic['data']['camp1']['team_name'] + 'vs' + dic['data']['camp2']['team_name'])
        with open('对战信息json.txt', 'a', encoding='utf-8') as file:
            file.write(pretty_json)
    else:
        print("对局信息请求失败，状态码：", response.status_code)


def table3(league_id):
    url = 'https://prod.comp.smoba.qq.com/leaguesite/league/team/settle_list/open?league_id=' + league_id
    response = requests.get(url, headers=head)
    # 检查响应是否成功
    if response.status_code == 200:
        pretty_json = pretty(response.json()['data'])
        with open('战队数据.txt', 'w', encoding='utf-8') as file:
            file.write(pretty_json)
    else:
        print("对局信息请求失败，状态码：", response.status_code)

    url2 = 'https://prod.comp.smoba.qq.com/leaguesite/league/player/settle_list/open?league_id=' + league_id
    response2 = requests.get(url2, headers=head)
    # 检查响应是否成功
    if response2.status_code == 200:
        pretty_json2 = pretty(response2.json()['data'])
        with open('选手数据.txt', 'w', encoding='utf-8') as file:
            file.write(pretty_json2)
    else:
        print("对局信息请求失败，状态码：", response2.status_code)

    url3 = 'https://prod.comp.smoba.qq.com/leaguesite/league/hero/settle_list/open?league_id=' + league_id
    response3 = requests.get(url3, headers=head)
    # 检查响应是否成功
    if response3.status_code == 200:
        pretty_json3 = pretty(response3.json()['data'])
        with open('英雄数据.txt', 'w', encoding='utf-8') as file:
            file.write(pretty_json3)
    else:
        print("对局信息请求失败，状态码：", response3.status_code)


def pretty(raw_json):
    return json.dumps(raw_json, indent=4, ensure_ascii=False)


if __name__ == '__main__':
    league_id = '20230002'  # 赛季编号
    # scheduleInfo(league_id)
    table3(league_id)
