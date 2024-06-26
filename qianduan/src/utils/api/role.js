import request from '@/utils/request'

export const login = (user_name,password) => {
    return request.post('/role/login', {
        user_name,
        password
    })
}

export const register = (user_name,password) => {
    return request.post('/role/register', {
        user_name,
        password
    })
}

export const getRole = (player_id,save_id) => {
    return request.post('/role/getRole', {
        player_id,
        save_id
    })
}
