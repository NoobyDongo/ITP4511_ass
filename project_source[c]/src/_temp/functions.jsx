export async function GetAllFrom(url, action) {
    return fetch(url, {
        headers: { "Access-Control-Allow-Origin": "*" }
    })
        .then((response) => {
            if (response.status === 200) {
                return response.json()
            }
        }).then((data) => {
            if (data) {
                var result = []
                for (var i in data)
                    result.push(data[i]);
                action && action(result);
            }
        })
}
export function CheckList(list, msg) {
    return (list && list.length > 0) ? false : (<div className="empty">{msg}</div>)
}