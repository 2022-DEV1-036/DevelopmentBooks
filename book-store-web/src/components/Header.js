import React from 'react'
import { Container, 
         FormControl,
         Badge , 
         Nav, 
         Navbar, 
         Dropdown 
        } from 'react-bootstrap'

import {FaShoppingCart} from 'react-icons/fa';

export const Header = () => {
  return (
<Navbar bg="dark" variant="dark" style={{height:80}}>
    <Container>
        <Navbar.Brand>
            <a href='/'>Shopping Cart</a>
        </Navbar.Brand>
        <Navbar.Text className='search'>
            <FormControl 
                style={{width:500}}
                placeholder='Search a product'
                className='m-auto'
            />
        </Navbar.Text>
        <Nav>
          <Dropdown alignRight>
            <Dropdown.Toggle variant="success">
                <FaShoppingCart color="white" fontsize="25px"  />
              <Badge>{10}</Badge>
            </Dropdown.Toggle>

            <Dropdown.Menu style={{ minWidth: 370 }}>
                <span style={{minWidth:370}}>Cart is Empty</span>
            </Dropdown.Menu>
          </Dropdown>
        </Nav>

    </Container>
</Navbar>
  )
}
