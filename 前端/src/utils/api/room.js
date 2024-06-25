import request from '@/utils/request'

export const getEvent = () => {
    return request.get('/room/roomEvent', {

    })
}

export const getRoom = (player_id,save_id) => {
    return request.post('/room/getRoom', {
        player_id,
        save_id
    })
}