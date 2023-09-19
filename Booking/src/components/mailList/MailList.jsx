import "./MailList.css"

const MailList = () => {
  return (
    <div className="mail">
        <h1>Save time, save money!</h1>
        <h3>Sign up and we'll send the best deals to you</h3>
        <div className="inputContainer">
            <input type="text" placeholder="Your email" />
            <button>subscribe</button>
        </div>
    </div>
  )
}

export default MailList