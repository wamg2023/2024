import request from '@/utils/request'

export const move = (room_id) => {
    return request.post('/game/move', {
        room_id
    })
}

export const goBack = () => {
    return request.post('/game/back', {

    })
}

export const startFight = () => {
    return request.get('/game/startFight', {

    })
}

export const useSkill = (skill_id) => {
    return request.post('/game/CastSkill', {
            skill_id
    })
}
