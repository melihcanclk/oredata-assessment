// layout for the app
import React from 'react';

export default function Layout(props: { children: React.ReactNode }) {
  return (
    <>
      <h1>Oredata</h1>
      <div>
        {props.children}
      </div>
    </>
  )
}