export default function App() {
    const [clientSecret, setClientSecret] = useState("");
  
    useEffect(() => {
  
        /*
      fetch("/create-payment-intent", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ key: ukey }),
      })
        .then((res) => {
          console.log('requesting payment intent')
          return res.json()
        })
        .then((data) => {
          console.log({status: data.status, total:data.total, sec:data.clientSecret});
          if(data.status === 0){
            setClientSecret(data.clientSecret);
            setTotal(data.total);
          }
          return {sec:data.clientSecret, status:data.status};
        })
        .then((data) => {
          const status = data.status, sec = data.sec
          console.log(data)
          if(status === 0){
            var j = JSON.stringify({ secret: sec })
          console.log(j)
          }
        });
        */
  
    }, []);
  
    const appearance = {
      theme: 'stripe',
    };
    const options = {
      clientSecret,
      appearance,
    };
  
    return (
    <div className="App">
        <div><h1>Access Denined</h1></div>
    </div>
    );
  }