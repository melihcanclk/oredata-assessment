import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Layout from "./Layout";
import { Homepage } from "./Homepage";

function RouteComponent() {
  return (
    <Layout>
      <Router>
        <Routes>
          <Route path="/" element={<Homepage />} />
          <Route path="*" element={<h1>Not Found</h1>} />
        </Routes>
      </Router>
    </Layout>

  )
}

export default RouteComponent;