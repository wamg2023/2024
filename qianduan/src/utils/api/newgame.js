import request from '@/utils/request'

export const newGame = (player_id,save_id) => {
    return request.post('/game/newGame', {
        player_id,
        save_id
    })
}