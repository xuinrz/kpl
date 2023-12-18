import request from "axios"
import config from "@/config"

export function showSchedule() {
    return request.create({
        baseURL: config.burl,
        url: '/schedule',
        method: 'GET',
        timeout: 10000
    })
}