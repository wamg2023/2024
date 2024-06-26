import request from '@/utils/request'

export const getBag = () => {
    return request.get('/item/queryItem', {
    })
}

export const push = (item_id) => {
    return request.post('/item/dropItem', {
        item_id,
    })
}

export const pull = (item_id) => {
    return request.post('/item/takeItem', {
            item_id
    })
}

export const eventPull = (item_id) => {
    return request.post('/item/getItem', {
        item_id
    })
}

export const useItem = (item_id) => {
    return request.post('/item/useItem', {
        item_id
    })
}


export const remove = (item_id) => {
    return request.post('/item/removeEquipment', {
        item_id
    })
}