// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-commande',
//   imports: [],
//   templateUrl: './commande.component.html',
//   styleUrl: './commande.component.css'
// })
// export class CommandeComponent {

// }


import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { NgModule } from '@angular/core';



interface OrderItem {
  name: string;
  quantity: number;
  unitPrice: number;
  totalPrice: number;
}

interface Table {
  id: string;
  name: string;
  orders: OrderItem[];
  total: number;
}

interface MenuCategory {
  name: string;
  items: MenuItem[];
}

interface MenuItem {
  name: string;
  price: number;
}

@Component({
  selector: 'app-commande',
  standalone: true,
  imports: [CommonModule,FormsModule], // 👈 très important
  templateUrl: './commande.component.html',
  styleUrls: ['./commande.component.css']
})
export class CommandeComponent implements OnInit {
  // Données de démonstration
  currentTable: Table = {
    id: 'T1',
    name: 'SALLE 1 (T1)',
    orders: [
      { name: 'Samba 75-cl', quantity: 2, unitPrice: 3.00, totalPrice: 6.00 },
      { name: 'Poulet rôti frites', quantity: 2, unitPrice: 23.00, totalPrice: 46.00 }
    ],
    total: 52.00
  };

  menuCategories: MenuCategory[] = [
    {
      name: 'BOISSONS',
      items: [
        { name: 'Samba 75-cl', price: 3.00 },
        { name: 'Ntamba', price: 5.00 },
        { name: 'Simba 75-cl', price: 3.00 },
        { name: 'Tombo 75-cl', price: 4.00 }
      ]
    },
    {
      name: 'PLATS',
      items: [
        { name: 'Poulet rôti frites', price: 23.00 },
        { name: 'Thieboudienne', price: 25.00 },
        { name: 'Yassa poulet', price: 22.00 },
        { name: 'Mafé', price: 24.00 }
      ]
    },
    {
      name: 'VINS',
      items: [
        { name: 'Vin Rouge', price: 15.00 },
        { name: 'Vin Rosé', price: 14.00 }
      ]
    }
  ];

  tables: Table[] = [
    { id: 'T1', name: 'SALLE 1 (T1)', orders: [], total: 0 },
    { id: 'T2', name: 'SALLE 2 (T2)', orders: [], total: 0 },
    { id: 'T3', name: 'TERRASSE (T3)', orders: [], total: 0 }
  ];

  selectedTableIndex: number = 0;
  subtotal: number = 0;
  taxRate: number = 0.18; // Taux de TVA à 18%
  taxAmount: number = 0;
  totalAmount: number = 0;
  note: string = '';
  numberOfGuests: number = 1;

  constructor() { }

  ngOnInit(): void {
    this.calculateTotals();
  }

  selectTable(index: number): void {
    this.selectedTableIndex = index;
    this.currentTable = this.tables[index];
    this.calculateTotals();
  }

  addItemToOrder(item: MenuItem): void {
    const existingItem = this.currentTable.orders.find(order => order.name === item.name);
    
    if (existingItem) {
      existingItem.quantity++;
      existingItem.totalPrice = existingItem.quantity * existingItem.unitPrice;
    } else {
      this.currentTable.orders.push({
        name: item.name,
        quantity: 1,
        unitPrice: item.price,
        totalPrice: item.price
      });
    }
    
    this.calculateTotals();
  }

  removeItemFromOrder(index: number): void {
    this.currentTable.orders.splice(index, 1);
    this.calculateTotals();
  }

  updateQuantity(index: number, change: number): void {
    this.currentTable.orders[index].quantity += change;
    
    if (this.currentTable.orders[index].quantity <= 0) {
      this.removeItemFromOrder(index);
    } else {
      this.currentTable.orders[index].totalPrice = 
        this.currentTable.orders[index].quantity * this.currentTable.orders[index].unitPrice;
      this.calculateTotals();
    }
  }

  calculateTotals(): void {
    this.subtotal = this.currentTable.orders.reduce((sum, item) => sum + item.totalPrice, 0);
    this.taxAmount = this.subtotal * this.taxRate;
    this.totalAmount = this.subtotal + this.taxAmount;
    
    // Mettre à jour le total de la table
    this.currentTable.total = this.totalAmount;
  }

  addGuest(): void {
    this.numberOfGuests++;
  }

  removeGuest(): void {
    if (this.numberOfGuests > 1) {
      this.numberOfGuests--;
    }
  }

  processPayment(): void {
    // Logique pour traiter le paiement
    alert(`Paiement de $${this.totalAmount.toFixed(2)} effectué pour la table ${this.currentTable.name}`);
    
    // Réinitialiser la commande
    this.currentTable.orders = [];
    this.calculateTotals();
  }

  splitBill(): void {
    // Logique pour diviser l'addition
    const amountPerGuest = this.totalAmount / this.numberOfGuests;
    alert(`Facture divisée: $${amountPerGuest.toFixed(2)} par invité (${this.numberOfGuests} invités)`);
  }
}